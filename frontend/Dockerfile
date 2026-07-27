FROM node:24
WORKDIR /app
RUN groupadd -r -g 10001 appgroup && useradd -r -u 10001 -g appgroup appuser
COPY package.json package-lock.json* ./
RUN npm ci
COPY --chown=10001:10001 . .
USER appuser
CMD ["npm", "run", "dev"]