def solution(ingredient):
    
    stack = []

    count = 0
    for v in ingredient:
        stack.append(v)
        if stack[-4:] == [1,2,3,1]:
            count += 1
            for _ in range(4):
                stack.pop()
    return count