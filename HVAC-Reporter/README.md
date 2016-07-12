## This is the REST api service for HVAC data analysis

...

__dependencies__
    * python 2.7
    * flask


__How to run?__
    * `$ python Main.py`
    * browse to `localhost:5000/api/hvac`


__api__ *endpoints*
    `<localhost>:<port>`
    | URL          | result        |
    |--------------|:-------------:|
    |/api/hvac     | all data      |
    |/api/hvac/<id>| data for <id> |


__How it works?__
    * First data is parsed from csv file which is result of `hadoop map reduce`
    * Then, data are rendered as JSON file in web-browser as per the api end point