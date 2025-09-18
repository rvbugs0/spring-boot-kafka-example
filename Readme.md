# Starting Kakfa Server

```dtd
docker pull apache/kafka:4.1.0
```
```dtd
docker run -p 9092:9092 apache/kafka:4.1.0
```

### Start both consumer and producer application

<hr/>

### Send Message from Postman/browser
```dtd
http://localhost:8081/api/publish?key=bollywood&value=Singham
```

### Messages will show up in console of consumer application