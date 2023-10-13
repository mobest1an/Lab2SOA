#!/bin/bash
set -e

psql --username postgres --dbname postgres <<-EOSQL
  CREATE DATABASE flat;
  CREATE ROLE flat WITH ENCRYPTED PASSWORD 'flat' LOGIN;
  GRANT ALL PRIVILEGES ON DATABASE flat TO flat;
EOSQL

psql --username postgres --dbname flat <<-EOSQL
  GRANT ALL ON SCHEMA public TO flat;
EOSQL
