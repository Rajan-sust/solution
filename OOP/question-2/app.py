from flask import Flask, request, render_template, redirect
from hashlib import sha256
from pymongo import MongoClient
from flask import abort

app = Flask(__name__)

client = MongoClient()
db = client['dsi']
collection = db['user']

def get_hash_of_pwd(pwd):
    return sha256(pwd.encode('utf-8')).hexdigest()

@app.route('/land/<status>', methods = ['GET'])
def lpage(status):
    msg = None
    if(status == 'success'):
        msg = 'Congratulations! You were successfully logged in.'
    else:
        msg = 'Invalid credentials'
    return render_template('lpage.html', msg=msg)

@app.route('/login', methods = ['GET', 'POST'])
def index():
    if request.method == 'GET':
        return render_template('index.html')
    else:
        email = request.form['email']
        pwd = request.form['pwd']
        hash_val = get_hash_of_pwd(pwd)
        result = collection.find_one({'email' : email, 'pwd' : hash_val})
        status = 'success' if result else 'error'
        return redirect(f'/land/{status}')

if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5000)