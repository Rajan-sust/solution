from flask import Flask
from flask import request
from flask import render_template
from flask import redirect
from hashlib import sha256
from pymongo import MongoClient
from flask import abort

app = Flask(__name__)

client = MongoClient()
db = client['dsi']
collection = db['user']

def get_hash_of_pwd(pwd):
    return sha256(pwd.encode('utf-8')).hexdigest()

@app.route('/lpage', methods = ['GET'])
def lpage():
    # if request.url == request.base_url:
    #     abort(404)
    return render_template('lpage.html')

@app.route('/login', methods = ['GET', 'POST'])
def index():
    if request.method == 'GET':
        return render_template('index.html')
    else:
        email = request.form['email']
        pwd = request.form['pwd']
        hash_val = get_hash_of_pwd(pwd)
        result = collection.find_one({'email' : email, 'pwd' : hash_val})
        ok = 'true' if result else 'false'
        return redirect(f'/lpage?success={ok}')

if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5000, debug=True)