FROM maven:3.8-openjdk-17-slim

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY . .

# Default command
CMD ["mvn", "test"]
