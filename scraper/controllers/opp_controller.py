from flask import request
from app import app
from models.opp_model import opp_model
obj = opp_model()
# auth = auth_model()

@app.route("/add", methods=["POST"])
def add_scrape_det():
    return obj.add_scrape_det(request.json)


@app.route("/scrape", methods=["POST"])
def scare_opp():
    return obj.scare_opp()

@app.route("/", methods=["POST"])
def get_opp():
    return obj.get_opp(request.json)

if __name__ == '__main__':
    app.run(debug=True)