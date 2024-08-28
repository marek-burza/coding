import random
import unittest
from functools import partial
from typing import Callable, List, Optional, Tuple

import torch
from torch import nn, optim


class SimpleNN(nn.Module):
    """
    A very basic FC network to model the distribution of some training data
    """

    def __init__(self, input_size: int = 8):
        super().__init__()
        self.fc1 = nn.Linear(input_size, 50)
        self.fc2 = nn.Linear(50, 2)

    def forward(self, x: torch.Tensor) -> torch.Tensor:
        x = torch.relu(self.fc1(x)).squeeze(1)
        x = self.fc2(x)
        return x


def _generate_training_batch(
    batch_size: int, input_dim: int
) -> Tuple[torch.Tensor, torch.Tensor]:
    """
    Generate a random input/label pair to use as input to the model.

    There are 2 classes, and a sample's class is determined
    by the sum of its values against a fixed threshold.
    """
    sample_shape = [batch_size, input_dim]
    input_sample = torch.randn(*sample_shape)
    label = (input_sample.view(batch_size, -1).sum(dim=1) > 0.325).long()
    return input_sample, label


def validate_model(model: SimpleNN):
    """
    Validate a provided model against unseen data.

    Generates a new batch of training data (32 samples),
    runs a forward pass of the server model and compares
    the predictions against the labels.

    Input:
        model: A SimpleNN model which will be evaluated against new data

    Returns:
        The proportion of correct predictions
    """
    new_sample, targets = _generate_training_batch(32, 8)

    result = model(new_sample)
    _, prediction = torch.max(result, dim=1)
    return sum(targets == prediction) / prediction.numel()


class Gateway:
    def __init__(
        self,
        name: str,
        batches_per_round: int = 4,
        loss_function: Callable = nn.CrossEntropyLoss,
        optimizer: Callable = partial(optim.Adam, lr=0.01),
    ):
        # IMPLEMENTATION: BEGIN
        # For training reproducibility
        # (assuming the same model instance is not retrained)
        torch.manual_seed(3)
        random.seed(3)
        # IMPLEMENTATION: END
        self.model = SimpleNN()
        self.name = name
        self.batches_per_round = batches_per_round
        # IMPLEMENTATION: BEGIN
        self.loss_function = loss_function()
        self.optimizer: optim.Optimizer = optimizer(self.model.parameters())
        # IMPLEMENTATION: END

    def fed_round(self):
        """
        Perform a single federation round of training using
        randomly generated data.

        This function should run a forward pass through the SimpleNN model,
        calculate the loss and update the optimizer.
        """
        # Zero gradients
        self.model.zero_grad()
        self.optimizer.zero_grad()
        for _ in range(self.batches_per_round):
            batch, targets = _generate_training_batch(8, 8)
            # IMPLEMENTATION: BEGIN
            # Forward pass
            outputs = self.model(batch)
            # Compute the loss
            loss = self.loss_function(outputs, targets)
            # Zero the gradients
            self.optimizer.zero_grad()
            # Backward pass
            loss.backward()
            # Update the model parameters (using the computed gradients)
            self.optimizer.step()
            # IMPLEMENTATION: END

    def update_model(self, state_dict: dict):
        """Update the gateway's local model with a new set of weights"""
        self.model.load_state_dict(state_dict)


class Orchestrator:
    def __init__(self, gateways: List[Gateway]):
        self.gateways = gateways
        self._server_model: Optional[nn.Module] = None

    def train_fed_avg(self, num_rounds: int):
        """
        Perform a federated training run for `num_rounds` rounds.

        Input:
            num_rounds: The number of training rounds to perform

        Returns:
            An aggregated model
        """
        # Initialise the server model at the start of the training
        server_model = SimpleNN()

        # IMPLEMENTATION: BEGIN
        for _ in range(num_rounds):
            # Repeat for each gateway until all rounds are complete:
            # Ensure each gateway has the same weights
            # at the start of federation round
            self._update_gateways(server_model.state_dict())
            for gateway in self.gateways:
                # Tell a gateway to run a local training round
                gateway.fed_round()
            # Aggregate the weights, and update the server model
            # with the aggregated weights
            server_model.load_state_dict(self._aggregate())
        # IMPLEMENTATION: END
        # Return the final aggregated model
        self._server_model = server_model
        return server_model

    def _aggregate(self) -> dict:
        """
        Iterates through all gateways connected to the orchestrator,
        reading each model's state_dict, and taking the average
        of the weights across the models.
        Returns:
            A state dict containing the averaged weights
        """
        # IMPLEMENTATION: BEGIN
        state_dict = {
            key: torch.mean(
                torch.stack(
                    [
                        gateway.model.state_dict()[key]
                        for gateway in self.gateways
                    ],
                    dim=0,
                ),
                dim=0,
            )
            for key in ['fc1.weight', 'fc1.bias', 'fc2.weight', 'fc2.bias']
        }
        return state_dict
        # IMPLEMENTATION: END

    def _update_gateways(self, state_dict: dict):
        """
        Update all gateways' models with the provided state dict
        """
        for gateway in self.gateways:
            gateway.update_model(state_dict)


def main() -> float:
    default_gateways = [Gateway(f'gateway_{i}', 4) for i in range(4)]

    orchestrator = Orchestrator(default_gateways)

    new_model = orchestrator.train_fed_avg(32)

    validation_accuracy = validate_model(new_model)
    return float(validation_accuracy * 100)


# IMPLEMENTATION: BEGIN
class TestCode(unittest.TestCase):
    def test_minimum_bar(self):
        self.assertTrue(all(main() > 80.0 for _ in range(10)))

    def test_reproducibility(self):
        self.assertEqual(len({main() for _ in range(10)}), 1)


# IMPLEMENTATION: END


if __name__ == '__main__':  # pragma: no cover
    print(f'Training Complete. Validation accuracy: {main():.2f}%')
