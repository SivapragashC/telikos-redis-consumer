# telikos-redis-consumer

## Add the below config in application.yml for TTL and redis server
	
	cache:
		ttl: <provide the ttl value here> (optional)
	
	redis:
		ssl: <provide the redis ssl here> (mandatory)
		host: <provide the redis host here> (mandatory)
		port: <provide the redis port here> (mandatory)
	
