FROM nginx:alpine

COPY ./dist/fe/browser /usr/share/nginx/html

# nginx:alpine schickt alles unter /etc/nginx/templates beim Start durch
# envsubst und legt das Ergebnis in /etc/nginx/conf.d ab.
COPY ./nginx.conf.template /etc/nginx/templates/default.conf.template

# Vorgabe passend zum Servicenamen in compose.yaml. Wer das Backend woanders
# stehen hat, setzt BACKEND_URL beim Start - das Image bleibt dasselbe.
ENV BACKEND_URL=http://be:8080

CMD ["nginx", "-g", "daemon off;"]
