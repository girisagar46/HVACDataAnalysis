## This is the REST api service for HVAC data analysis

_____



__dependencies__
    
* python 2.7
* flask


__How to run?__

* $ python Main.py
* browse to localhost:5000/api/hvac


__api__ *endpoints*

`<localhost>:<port>`

| URL               | result                    |
|-------------------|:-------------------------:|
|/api/hvac          | all data                  |
|/api/hvac/14       | data for building 14      |

__How it works?__

* First data is parsed from csv file which is result of __hadoop map reduce__
* Then, data are rendered as JSON file in web-browser as per the api end point
