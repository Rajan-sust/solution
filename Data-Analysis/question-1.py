import argparse

def read_data(path):
    with open(path, mode='r', encoding='utf-8') as file:
        lines = file.read().splitlines()
    return lines

def make_tuple(data):
    def get_customer_name(line):
        for field in line.split(','):
            if field.startswith('01'):
                return field[2::]
        # end
    def get_account_number(line):
        for field in line.split(','):
            if field.startswith('02'):
                return field[2::]
        # end

    indexs = [index for index, line in enumerate(data) if line.startswith('AAAA')] + [len(data)]
    tuples = []
    for i in range(len(indexs) - 1):
        no_of_lines = indexs[i+1] - indexs[i]
        account_no = get_account_number(data[indexs[i]])
        customer_name = get_customer_name(data[indexs[i]])
        start_line_no = indexs[i]
        tuples.append((no_of_lines, account_no, customer_name, start_line_no))
    return tuples

def show_account_number(tuples):
    print('All account numbers:')
    print('\n'.join(account_no for _, account_no, _, _ in tuples))

def show_account_more_than_fifty_lines(tuples):
    print('All account numbers that has more then 50 lines:')
    print('\n'.join(account_no for no_of_lines, account_no, _, _ in tuples if no_of_lines > 50))
    
# If tie occurs, selecting one randomly 
def show_account_with_highest_number_of_lines(data, tuples):
    print('All lines:')
    tup = max(tuples, key=lambda x: x[0])
    for no in range(tup[3], tup[3] + tup[0]):
        print(data[no])
def show_account_name_account_no_total_lines(tuples):
    print('account_name---account_number--total_lines')
    for no_of_lines, account_no, customer_name, _ in tuples:
        print(f'{customer_name}---{account_no}---{no_of_lines}')

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--path', type=str, required=True, help='path of data')
    args = parser.parse_args()
    
    data = read_data(args.path)
    tuples = make_tuple(data)
    show_account_number(tuples)
    show_account_more_than_fifty_lines(tuples)
    show_account_with_highest_number_of_lines(data, tuples)
    show_account_name_account_no_total_lines(tuples)

if __name__ == "__main__":
    main()
# python3 main.py -h
# python3 main.py --path data.txt