FROM ubuntu:latest
LABEL authors="anand"

ENTRYPOINT ["top", "-b"]

# Use the official PostgreSQL image as the base image
FROM postgres:latest

# Set environment variables for the PostgreSQL container
ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=root
ENV POSTGRES_DB=tododb
ENV POSTGRES_CONTAINER_NAME=postgres_container

# Create a Docker volume for data persistence
VOLUME /var/lib/postgresql/data

# Expose the PostgreSQL default port
EXPOSE 5432
