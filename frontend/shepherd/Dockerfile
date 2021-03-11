# Pull the official base image
FROM node:alpine3.13

# Set your working directory
WORKDIR /usr/src/app

# Add `/usr/src/app/node_modules/.bin` to $PATH
ENV PATH /usr/src/app/node_modules/.bin:$PATH

# Install application dependencies
COPY package*.json ./
RUN npm install --silent

# Add app
COPY . ./

# Will run the app
CMD ["ng", "serve", "--host=0.0.0.0"]
