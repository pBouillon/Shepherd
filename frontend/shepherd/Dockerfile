# Pull the official base image  
FROM node:alpine3.13
 
# Set your working directory  
WORKDIR /app  
 
# Add `/app/node_modules/.bin` to $PATH  
ENV PATH /app/node_modules/.bin:$PATH  
 
# Install application dependencies
COPY package*.json ./
RUN npm install --silent
 
# Add app  
COPY . ./  
 
# Will run app  
CMD ["npm", "start"] 
