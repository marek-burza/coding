class DomainError(Exception):
    code = "domain_error"


class UnknownAccount(DomainError):
    code = "unknown_account"


class UnknownCustomer(DomainError):
    code = "unknown_customer"


class InsufficientFunds(DomainError):
    code = "insufficient_funds"


class SelfTransfer(DomainError):
    code = "self_transfer"


class IdempotencyConflict(DomainError):
    code = "idempotency_conflict"
