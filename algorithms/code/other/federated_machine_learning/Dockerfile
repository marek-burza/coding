FROM python:3.12-slim

ADD requirements.txt /tmp/requirements.txt
RUN pip3 install --disable-pip-version-check -r /tmp/requirements.txt && rm /tmp/requirements.txt

ADD federated_machine_learning.py /app/app.py

WORKDIR /app

ENTRYPOINT [ "/usr/local/bin/python", "/app/app.py" ]
