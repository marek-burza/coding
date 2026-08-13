#!/bin/sh

set -e

curl -s -X PUT 'http://localhost/customers/?customer_identifier=1&customer_name=John%20Doe'
curl -s -X PUT 'http://localhost/customers/?customer_identifier=2&customer_name=Jack%20Smith'
curl -s -X PUT 'http://localhost/customers/?customer_identifier=3&customer_name=Jane%20Taylor'
curl -s -X PUT 'http://localhost/customers/?customer_identifier=4&customer_name=Jade%20Wilson'
