def first_repetitive_char(text):
    hash_set = set()
    for ch in text:
        if ch in hash_set:
            print(ch)
            return
        hash_set.add(ch)
    print('-1')
    return

def main():
    _ = input() # ignoring n
    text = input()
    first_repetitive_char(text)

if __name__ == "__main__":
    main()