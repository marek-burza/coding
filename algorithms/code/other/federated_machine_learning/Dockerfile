FROM python:3.12-slim

COPY requirements.txt /tmp/requirements.txt
RUN pip3 install --disable-pip-version-check --no-cache-dir -r /tmp/requirements.txt && rm /tmp/requirements.txt

COPY federated_machine_learning.py /app/app.py

WORKDIR /app

RUN useradd -m user
USER user

ENTRYPOINT [ "/usr/local/bin/python", "/app/app.py" ]
