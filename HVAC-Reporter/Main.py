from flask import Flask, jsonify
from services import CSVparser
from flask import make_response

app = Flask(__name__)


@app.route('/api/hvac/', methods=['GET'])
def index():
    data_to_render = CSVparser.get_data()
    return jsonify(data=data_to_render)


@app.route('/api/hvac/<int:id>', methods=['GET'])
def get_bid(id):
    bid = [bu_id for bu_id in CSVparser.get_data() if bu_id['bid'] == id]
    if len(bid) == 0:
        return make_response(jsonify({'error': 'Not found'}), 404)
    return jsonify({"building" : bid[0]})


if __name__ == '__main__':
    app.run(debug=True)
