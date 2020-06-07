'''
将penalLaw.txt中的法条信息结构化处理，形成penalLaw.xml中的信息
penalLaw.xml用在init.sql中初始化statureTable
'''

import re
import os
from xml.dom.minidom import Document

srcPath = os.path.dirname(os.getcwd())
penalPath = os.path.join(srcPath, 'penalLaw.txt')

def extractLaw(lawDict):
    lawInd = 0
    lawLst = list()
    civil = open(penalPath, 'r')
    rawLaw = civil.read()
    civil.close()
    parPattern = re.compile(r'第[零一二三四五六七八九十百]+编.*')
    chaPattern = re.compile(r'第[零一二三四五六七八九十百]+章.*')
    rawLaw = re.sub(parPattern, '', rawLaw)
    rawLaw = re.sub(chaPattern, '', rawLaw).strip()
    artPattern = re.compile(r'第([零一二三四五六七八九十百]+)条\s')
    laws = re.split(artPattern, rawLaw)
    for idx in range(1, len(laws)):
        law = re.sub(r'\n', ' ', laws[idx]).strip()
        checkPattern = re.compile(r'[零一二三四五六七八九十百]+')
        check = checkPattern.match(law)

        if idx % 2 == 1:
            if check is None or check.end() != len(law):
                raise Exception("Match Interruption")

            if law not in lawLst:
                lawInd += 1
                index = law
            else:
                raise Exception("Repetition Interruption")
        else:
            if check is None or check.end() != len(law):
                law = re.sub(r'\u3000','',law)
                lawDict[(lawInd)] = law.split(' ',1)
                lawLst.append(index)
            else:
                raise Exception("Match Interruption")

    return lawDict

lawDict = {}
extractLaw(lawDict)
for key in lawDict:
    if key <=12:
        lawDict[key].append([1,1])
    elif key<=31:
        lawDict[key].append([1,2])
    elif key<=60:
        lawDict[key].append([1,3])
    elif key<=89:
        lawDict[key].append([1,4])
    elif key<=101:
        lawDict[key].append([1,5])
    elif key<=113:
        lawDict[key].append([2,1])
    elif key<=139:
        lawDict[key].append([2,2])
    elif key<=231:
        lawDict[key].append([2,3])
    elif key<=262:
        lawDict[key].append([2,4])
    elif key<=276:
        lawDict[key].append([2,5])
    elif key<=367:
        lawDict[key].append([2,6])
    elif key<=381:
        lawDict[key].append([2,7])
    elif key<=396:
        lawDict[key].append([2,8])
    elif key<=419:
        lawDict[key].append([2,9])
    else:
        lawDict[key].append([2,10])
print(lawDict)



doc = Document()
#DOCUMENT = doc.createElement('penalLaw')
doc.appendChild(DOCUMENT)


def ins_element(name, text, father):
    element = doc.createElement(name)
    father.appendChild(element)

    Text = doc.createTextNode(text)
    element.appendChild(Text)


for key in lawDict:
    father = doc.createElement('row')
    DOCUMENT.appendChild(father)
    ins_element('law_id', str(key), father)

    if len(lawDict[key]) == 3:
        ins_element('part', str(lawDict[key][2][0]), father)
        ins_element('chapter', str(lawDict[key][2][1]), father)
        ins_element('article', str(key), father)
        ins_element('title', str(lawDict[key][0]), father)
        ins_element('content', str(lawDict[key][1]), father)
    else:
        ins_element('part', str(lawDict[key][1][0]), father)
        ins_element('chapter', str(lawDict[key][1][1]), father)
        ins_element('title', '本条删除', father)
        ins_element('content', str(lawDict[key][0]), father)
    ins_element('clickrate', '0', father)

f = open('penalLaw.xml', 'w')
doc.writexml(f, indent='\t', newl='\n', addindent='\t', encoding='utf-8')
f.close()