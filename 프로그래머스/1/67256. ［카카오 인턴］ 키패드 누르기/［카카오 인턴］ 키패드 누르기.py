def location(current,nextloca):
    dic={1:[0,0],2:[0,1],3:[0,2],
    4:[1,0],5:[1,1],6:[1,2],
    7:[2,0],8:[2,1],9:[2,2],
    '*':[3,0],0:[3,1],'#':[3,2]}
    x1,y1=dic[current]
    x2,y2=dic[nextloca]
    
    return abs(x1-x2) + abs(y1-y2)



def solution(numbers, hand):
    answer = ''

    lefthand='*'
    righthand='#'
    

    for i in numbers:
        if i==1 or i== 4 or i == 7:
            answer += 'L'
            lefthand=i
        elif i == 3 or i==6 or i==9:
            answer += 'R'
            righthand=i
        elif i==2 or i==5 or i==8 or i==0:
            if location(i,righthand) >location(i,lefthand):
                answer += 'L'
                lefthand =i
            elif location(i,righthand) <  location(i,lefthand):
                answer += 'R'
                righthand=i
            elif location(i,righthand) == location(i,lefthand) and hand == 'right':
                answer += 'R'
                righthand=i
            elif location(i,righthand) == location(i,lefthand) and hand == 'left':
                answer += 'L'
                lefthand =i
                
    return answer