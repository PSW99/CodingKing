def main():
    numbers = list(map(int, input().split()))
    chess = [1,1,2,2,2,8]

    for i in range(len(numbers)):
        print(chess[i]-numbers[i], end=' ')





if __name__ == '__main__':
    main()







