import requests
from bs4 import BeautifulSoup

res = requests.get(
    'https://en.wikipedia.org/wiki/Category:The_Lord_of_the_Rings_characters')

soup = BeautifulSoup(res.content, 'html.parser')
aTags = soup.select('div#mw-pages div.mw-category-group > ul > li > a')

names = ['"{}"'.format(aTag.contents[0]) for aTag in aTags]
print(*names, sep=', ')
