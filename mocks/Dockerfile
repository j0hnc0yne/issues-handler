FROM wiremock/wiremock
# Use the official WireMock image as the base image
# Set the working directory
WORKDIR /home/wiremock
# Copy the mappings and files directories into the container
COPY ./wiremock/mappings /home/wiremock/mappings
COPY ./wiremock/__files /home/wiremock/__files
# Expose the WireMock port
EXPOSE 9080
# Start WireMock with the specified options
CMD ["--verbose", "--no-request-journal", "--global-response-templating", "--port", "9080"]
