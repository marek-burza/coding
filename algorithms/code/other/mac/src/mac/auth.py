import hashlib
import os
import secrets
from typing import Annotated

from fastapi import Depends, HTTPException, status
from fastapi.security import HTTPBasic, HTTPBasicCredentials

BASIC_AUTH = os.environ.get("MAC_BASIC_AUTH", "").strip()

# OWASP recommends 2**17 but this is not a production-grade deployment
SCRYPT_N = 2**15
SCRYPT_R = 8
SCRYPT_P = 1
SCRYPT_MAXMEM = 2 * 128 * SCRYPT_N * SCRYPT_R


def hash_password(password: str, salt: bytes) -> str:
    return hashlib.scrypt(
        password.encode(),
        salt=salt,
        n=SCRYPT_N,
        r=SCRYPT_R,
        p=SCRYPT_P,
        maxmem=SCRYPT_MAXMEM,
    ).hex()


_basic_auth = HTTPBasic(auto_error=False)


def require_auth(
    credentials: Annotated[HTTPBasicCredentials | None, Depends(_basic_auth)],
) -> None:
    if not BASIC_AUTH:
        return
    if credentials is None:
        raise HTTPException(
            status.HTTP_401_UNAUTHORIZED,
            detail="Not authenticated",
            headers={"WWW-Authenticate": "Basic"},
        )
    username, salt, stored = BASIC_AUTH.split(":", 2)
    presented = hash_password(credentials.password, bytes.fromhex(salt))
    matches = secrets.compare_digest(credentials.username.encode(), username.encode())
    matches &= secrets.compare_digest(presented.encode(), stored.encode())
    if not matches:
        raise HTTPException(
            status.HTTP_401_UNAUTHORIZED,
            detail="Not authenticated",
            headers={"WWW-Authenticate": "Basic"},
        )
