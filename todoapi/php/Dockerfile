## Stage fpm

FROM php:7.4-fpm-alpine AS todo_api_fpm
COPY ./todoapi /var/www/html
COPY --from=composer:2.0.12 /usr/bin/composer /usr/bin/composer
RUN apk --no-cache add postgresql-dev && \
    docker-php-ext-install pdo pdo_pgsql &&\
    chown -R www-data:www-data /var/www/html/ && \
    cd /var/www/html && \
    composer install && \
    rm -rf /usr/bin/composer

WORKDIR /var/www/html

USER www-data

## Stage nginx


FROM nginx:alpine AS todo_api_nginx
COPY ./.docker/nginx/api.conf /etc/nginx/conf.d/default.conf
RUN mkdir -p /var/www/html/public && \
    echo "<?php echo \"hello\"?>" > /var/www/html/public/index.php
WORKDIR /var/www/html/public

USER $user