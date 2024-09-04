FROM python:3.12-alpine

ADD . /tmp/banking

RUN cd /tmp/banking && \
    pip install --disable-pip-version-check --no-cache-dir . && \
    rm -rf /tmp/banking

WORKDIR /var/db

ENTRYPOINT [ "/usr/local/bin/python", "/usr/local/lib/python3.12/site-packages/banking/main.py" ]
