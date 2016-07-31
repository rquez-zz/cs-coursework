FROM tutum/lamp:latest
RUN rm -fr /app
COPY src/ /var/www/html/
EXPOSE 80 3306
CMD ["/run.sh"]
