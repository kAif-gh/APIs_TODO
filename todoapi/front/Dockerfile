ARG NODE_VERSION=14
ARG NGINX_VERSION=1.19.3-alpine

##stage node build

FROM node:${NODE_VERSION} AS todo_ui_build
WORKDIR /ng-app/todo-ui
COPY . .
RUN npm install && \
    npm run build --prod --output-path=dist

## stage nginx

FROM nginx:${NGINX_VERSION} AS todo_ui
COPY .docker/nginx/default.conf /etc/nginx/conf.d
COPY .docker/nginx/docker-entrypoint.sh /docker-entrypoint.sh
COPY --from=todo_ui_build /ng-app/todo-ui/dist/front /ng-app-root
ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["nginx", "-g", "daemon off;"]
