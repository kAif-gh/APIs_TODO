FROM python:3.9 AS todo_api

ENV WERKZEUG_RUN_MAIN=true \
    PYTHONUNBUFFERED=1 \
    PYTHONIOENCODING=UTF-8 \
    API_PYTHON_HOST=0.0.0.0 \
    API_PYTHON_PORT=5000

COPY ./ /todo_api
WORKDIR /todo_api
RUN apt update && \
    apt upgrade -y && \
    pip install --no-cache-dir -r requirements.txt && \
    rm -rf /var/lib/apt/lists/*

CMD ["python", "-u", "app.py"]

FROM todo_api AS todo_unit_tests
WORKDIR /todo_api

RUN pip install --no-cache-dir -r tests/requirements.txt

CMD ["python", "-m", "unittest"]
