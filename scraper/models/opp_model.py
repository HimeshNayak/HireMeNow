from flask import make_response, jsonify
from configs.config import dbconfig
import pymongo
from helper.scrape_helper import scrape_data


class opp_model():
    def __init__(self):
        client = pymongo.MongoClient("mongodb+srv://hiremenow:hiremenow@cluster0.qw6qzls.mongodb.net/?retryWrites=true&w=majority")
        self.db=client['opportunity']
        
    def add_scrape_det(self, data):
        try:
            detail_id = self.db.opportunitydetails.insert_one(data).inserted_id
            if detail_id:
                return make_response({"message": "success", "detail_id": detail_id},200)
            else:
                return make_response({"message": "failure"},400)
        except:
            return make_response({"message": "failure"},500)
    
    def scare_opp(self):
        available_sites = list(self.db.opportunitydetails.find({"careerSite.category": "Software Engineering"}))
        job_opp = []
        for site in available_sites:
            new_opp = scrape_data(site)
            job_opp.extend(new_opp)
            
        self.db.scrapedopportunities.insert_many(job_opp)
        return {"message": "success"}
    
    def get_opp(self,data):
        available_opp = list(self.db.scrapedopportunities.find({"category": data["category"]}))
        print(available_opp)
        return jsonify(available_opp)