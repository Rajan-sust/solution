import argparse

def read_data(path):
    with open(path, mode='r', encoding='utf-8') as file:
        lines = file.read().splitlines()
    return lines

def show_account_number(data):
    def extract(line):
        account_no = line.split(',')[2]
        return account_no[2::]
    print('All account numbers:')
    for line in data:
        if line.startswith('AAAA'):
            print(extract(line))
    return
def show_account_more_than_fifty(data):
    data.append('AAAA,dummy...')
    
    data.pop()


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--path', type=str, required=True, help='path of data')
    args = parser.parse_args()
    
    data = read_data(args.path)
    show_account_number(data)
    

if __name__ == "__main__":
    main()
# python3 main.py -h