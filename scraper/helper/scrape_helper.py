from selenium import webdriver
from bs4 import BeautifulSoup
import time

def scrape_data(site_obj):
    # specify the url
    url = f"{site_obj['baseUrl']}{site_obj['careerSite']['url']}"
    

    # create a new chrome browser
    driver = webdriver.Chrome()

    # open the url
    driver.get(url)

    # wait for the page to load
    time.sleep(5)

    # get the html source of the page
    html = driver.page_source

    # close the browser
    driver.close()

    # parse the html using beautifulsoup
    soup = BeautifulSoup(html, 'html.parser')
    listTag = site_obj['listObj']['tag']
    listClass = site_obj['listObj']['className']
    job_opportunities = soup.find_all(listTag, {"class":listClass}, limit=3)
    
    job_list = []
    # loop through the job opportunities
    for job in job_opportunities:
        # extract the job title
        titleTag = site_obj['title']['tag']
        titleClass = site_obj['title']['className']
        title = job.find(titleTag, {"class":titleClass}).text
        # extract the apply link
        applyTag = site_obj['apply']['tag']
        applyClass = site_obj['apply']['className']
        apply_link = job.find(applyTag, {"class":applyClass})["href"]
        job_list_data = {"title":title, "apply_link": apply_link, "base_url": site_obj['baseUrl'], "company": site_obj['company']}
        
        job_list.append(job_list_data)

    return job_list

