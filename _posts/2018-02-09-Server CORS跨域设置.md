---
layout: post
title: CORS跨域设置
categories: [Java]
tags: [Java, Nginx]
fullview: false
comments: true
---

#### 1， `Nginx`

```sh
#
# Wide-open CORS config for nginx
#
location / {
     if ($request_method = 'OPTIONS') {
        add_header 'Access-Control-Allow-Origin' '*';
        add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
        #
        # Custom headers and headers various browsers *should* be OK with but aren't
        #
        add_header 'Access-Control-Allow-Headers' 'DNT,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range';
        #
        # Tell client that this pre-flight info is valid for 20 days
        #
        add_header 'Access-Control-Max-Age' 1728000;
        add_header 'Content-Type' 'text/plain; charset=utf-8';
        add_header 'Content-Length' 0;
        return 204;
     }
     if ($request_method = 'POST') {
        add_header 'Access-Control-Allow-Origin' '*';
        add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
        add_header 'Access-Control-Allow-Headers' 'DNT,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range';
        add_header 'Access-Control-Expose-Headers' 'Content-Length,Content-Range';
     }
     if ($request_method = 'GET') {
        add_header 'Access-Control-Allow-Origin' '*';
        add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
        add_header 'Access-Control-Allow-Headers' 'DNT,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Range';
        add_header 'Access-Control-Expose-Headers' 'Content-Length,Content-Range';
     }
}
```

简写版本

```nginx
add_header 'Access-Control-Allow-Origin' '*';
add_header 'Access-Control-Allow-Credentials' 'true';
add_header 'Access-Control-Allow-Headers' 'Authorization,Content-Type,Accept,Origin,User-Agent,DNT,Cache-Control,X-Mx-ReqToken';
add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS, PUT, DELETE';
if ($request_method = 'OPTIONS') {
    return 204;
}
```

Full sample

`nginx.conf`
```sh
user  nginx;
worker_processes  1;

error_log  /var/log/nginx/error.log warn;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;
    client_max_body_size 20m;
    client_body_timeout 60s;
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;
    proxy_read_timeout 180s;    

    #gzip  on;

    include /etc/nginx/conf.d/*.conf;
}

```

`api.conf`

```sh
server {
    listen       80;
    #server_name  localhost;
    server_name api.xxx.com;	

    #charset koi8-r;
    #access_log  /var/log/nginx/log/host.access.log  main;
    
    add_header 'Access-Control-Allow-Origin' '*';
    add_header 'Access-Control-Allow-Credentials' 'true';
    add_header 'Access-Control-Allow-Headers' 'Authorization,Content-Type,Accept,Origin,User-Agent,DNT,Cache-Control,X-Mx-ReqToken';
    add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS, PUT, DELETE';
    
    if ($request_method = 'OPTIONS') {
        return 204;
    }

    location / {
        proxy_pass      http://127.0.0.1:8080;
        index  index.html;
        proxy_set_header        X-Real-IP       $remote_addr;
        proxy_set_header        Host            $host;  
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```



#### 2, `Apache`

To add the `CORS` authorization to the header using Apache, simply add the following line inside either the
 `<Directory>`, `<Location>`, `<Files>` or `<VirtualHost>` sections of your server config (usually located in a `*.conf` file, 
 such as `httpd.conf` or `apache.conf`), or within a `.htaccess` file: 

```markdown
Header set Access-Control-Allow-Origin "*"
```

To ensure that your changes are correct, it is strongly recommended that you use 

```markdown
apachectl -t
```

检查配置

```markdown
sudo service apache2 reload
```

or

```markdown
apachectl -k graceful
```


#### 3, `Tomcat`

http://tomcat.apache.org/tomcat-7.0-doc/config/filter.html#CORS_Filter

```markdown
<filter>
  <filter-name>CorsFilter</filter-name>
  <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
</filter>
<filter-mapping>
  <filter-name>CorsFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```