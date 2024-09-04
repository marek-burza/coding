import uuid
from typing import Callable


def cleanse_string(
    string: str, cleansing_function: Callable[[str], bool]
) -> str:
    string = ''.join(
        [
            character if cleansing_function(character) else ' '
            for character in string
        ]
    )
    while '  ' in string:
        string = string.replace('  ', ' ')
    return string.strip()


def cleanse_customer_name(customer_name: str) -> str:
    return cleanse_string(customer_name, lambda character: character.isalpha())


def is_identifier_valid(identifier: str) -> bool:
    try:
        uuid.UUID(identifier, version=4)
    except ValueError:
        return False
    return True
