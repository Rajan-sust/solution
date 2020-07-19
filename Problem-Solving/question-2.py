def solver(n):
    count = 0
    while n:
        if n & 1:
            count += 1
        n >>= 1
    return count

def main():
    n = int(input())
    print(solver(n))

if __name__ == "__main__":
    main()