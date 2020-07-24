import argparse
from collections import defaultdict


ADDRESS_START = 'STATEMENT DATE PAGE'
TRANSACTION = 'TRANSACTION DESCRIPTION____________________DATE_________AMOUNT__________BALANCE'

hash_map = defaultdict(list)


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


def get_address_transaction(modified_data):
    def helper(k):
        key = ' '.join(modified_data[k].split()[:-3:])
        key += '\n' + ' '.join(modified_data[k+1].split()[:-2:])
        key += '\n' + modified_data[k+2]
        next_idx = None
        n = len(modified_data)
        for i in range(k+3, n):
            if modified_data[i] == TRANSACTION:
                next_idx = i + 1
                break
        for i in range(next_idx, n):
            if modified_data[i].startswith('TRANSACTION'):
                hash_map[key].append(modified_data[i])
            else:
                break
    # end
    for idx, line in enumerate(modified_data):
        if line.endswith(ADDRESS_START):
            helper(idx)

def show_output():
    def format_transaction(t):
        _ , des, date, amount, balance = t.split()
        return 'TRANSACTION {}                            {}{:14.2f}         {}'.format(des, date, float(amount), balance)
    for key, val in hash_map.items():
        print('\n')
        print(key)
        print('\n')
        for t in val:
            print(format_transaction(t))
    

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--path', type=str, required=True, help='path of data')
    args = parser.parse_args()
    data = read_data(args.path)
    modified_data = pre_process_data(data)
    # print(modified_data)
    get_address_transaction(modified_data)
    # print(hash_map.values())
    show_output()
if __name__ == "__main__":
    main()