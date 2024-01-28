from collections import defaultdict,Counter

def solution(participant, completion):
    answer = ''
    par=Counter(participant)
    com=Counter(completion)
    
    answer= par-com
    print(answer)
    
    return list(answer.keys())[0]