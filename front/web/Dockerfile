FROM node:lts-alpine as builder
WORKDIR /app
COPY package.json ./
RUN yarn install
COPY . .
RUN npm run build

FROM nginx
WORKDIR /app
COPY --from=builder /app/build /app/build
RUN rm /etc/nginx/conf.d/default.conf
mkdir /var/certs
COPY ./certs /var/certs
COPY ./nginx.conf /etc/nginx/conf.d
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
