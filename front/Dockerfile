# Étape 1 : Build de l'application Angular
FROM node:22.12.0 AS build

WORKDIR /app

ARG BACKEND_URL
ENV BACKEND_URL=$BACKEND_URL

COPY package.json package-lock.json ./
RUN npm install --legacy-peer-deps

COPY . .
# pour lancer en local 
# RUN npm run build local
RUN npm run build

# Étape 2 : Servir avec Nginx en mode Rootless
FROM nginx:1.25.4-alpine

USER root

# Ajuster les permissions pour l'utilisateur nginx
RUN mkdir -p /var/cache/nginx /tmp && \
    chown -R nginx:nginx /var/cache/nginx /usr/share/nginx/html /etc/nginx /var/log/nginx /tmp

# Copier les fichiers de build Angular
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

# Copier les configurations Nginx
COPY public/nginx/nginx.conf /etc/nginx/nginx.conf
COPY public/nginx/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 9090

# Passer à l'utilisateur nginx non privilégié
USER nginx

CMD ["nginx", "-g", "daemon off;"]
