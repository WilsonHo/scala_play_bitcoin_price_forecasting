**`Project Structure`**
 - 

 - Api module: This module includes REST api:
   + get prices **/api/v1/prices/:currency/:from/:to/**
   - currency: name of currency, ex: bitcoin
   - from: start time want to query base on epoch time
   - to: end time want to query base on epoch time
    ex: http://localhost:9000/api/v1/prices/bitcoin/1420070640000/1421311441000/
    (This endpoint hasn't finished yet, I need to get id of currency from name)
    
    
   + price forecasting  **/api/v1/price_forecasting/:currency**
    - currency: name of currency, ex: bitcoin
    ex: http://localhost:9000/api/v1/price_forecasting/btc
     (I stuck here with **jep** lib, this lib is using to call python code form scala)
 - Docker: This module includes docker-compose file to create environment
    cd ./Docker/Postgres/ && docker-compose up -d
    (I use postgres to store currency price)
    cd ./Docker/Redis/ && docker-compose up -d
    (I use redis to cache data, but I just a little bit)
 - Model: This module store all data model of the project, e.g. **Currency**, **CurrencyInfo**
 - scheduler: This module is used create crawls data job
 - spark: This module is used create training model offline for price forecasting
 - util: This module includes all utilities for other modules


How to run project
 - 
 
- Step 1: Create environment
$ cd ./Docker/Postgres/ && docker-compose up -d
$ cd ./Docker/Redis/ && docker-compose up -d

- Step 2: Create database schema from sql file
 ./api/conf/evolutions/default/1.sql

- Step 3: Run crawl data job, this job now run only one time
$ sbt scheduler/run

- Step 4: Run api with play framework
$ sbt api/run

In the ./spark/src/main/python directory I have 2 price forecasting files,
I get them from kaggle, I can run them to predict the bitcoin price.

pip install jep
pip install pandas
pip install numpy
pip install tensorflow
pip install keras
pip install sklearn
pip install html5lib
pip install bs4


sbt "-Djava.library.path=./lib" api/run
sbt "-Dhttp.port=8100 -Djava.library.path=./lib -jvm-debug 9999" api/run