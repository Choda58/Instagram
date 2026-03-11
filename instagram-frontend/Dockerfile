# Build stage
FROM node:20 AS build

WORKDIR /app

# Install dependencies for build
COPY package*.json ./
RUN npm install

# Build the Angular app (includes SSR output)
COPY . .
RUN npm run build

# Runtime stage
FROM node:20-slim AS runtime

WORKDIR /app

# Only install runtime dependencies
COPY package*.json ./
RUN npm install --omit=dev

# Copy built output
COPY --from=build /app/dist ./dist

EXPOSE 4000
ENV PORT=4000

ENV NG_ALLOWED_HOSTS="localhost"

# Start the SSR server
CMD ["node", "dist/instagram-frontend/server/server.mjs"]
