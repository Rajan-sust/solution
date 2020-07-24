import argparse



ADDRESS_START = 'STATEMENT DATE PAGE'
ADDRESS_END = 'STATEMENT PERIOD'

TRANSACTION = 'TRANSACTION DESCRIPTION____________________DATE_________AMOUNT__________BALANCE'

def read_data(path):
    with open(path, mode='r', encoding='utf-8') as file:
        lines = file.read().splitlines()
    return lines

def pre_process_data(data):
    modified_data = []
    for line in data:
        text = line.strip()
        if not text:
            continue
        modified_data.append(' '.join(text.split()))
    return modified_data


def user_address_finder(modified_data):
    def store(start_index):
        ans = ''
        for idx, line in enumerate(modified_data):
            if line == 
            if ans == '':
                ans += ' '.join(line.split()[:-3:])
            

    for idx, line in enumerate(modified_data):
        if line.startswith(ADDRESS_START):




def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--path', type=str, required=True, help='path of data')
    args = parser.parse_args()
    data = read_data(args.path)
    modified_data = pre_process_data(data)

if __name__ == "__main__":
    main()