from dataclasses import asdict
from typing import List

from requests import post

from media import Media


API_URI = 'http://localhost:8080/api'


DEFAULT_MEDIAS = [
    Media(
        name='Le Monde',
        description='Le Monde is a French daily afternoon newspaper. It is the main publication of Le Monde Group and reported an average circulation of 323,039 copies per issue in 2009, about 40,000 of which were sold abroad.',
        website='https://www.lemonde.fr/'),
    Media(
        name='Le HuffPost',
        description='HuffPost (formerly The Huffington Post till 2017, and sometimes abbreviated HuffPo) is an American news aggregator and blog, with localized and international editions.',
        website='https://www.huffingtonpost.fr/'),
    Media(
        name='CNN',
        description='Cable News Network is a multinational news-based pay television channel headquartered in Atlanta. It is owned by CNN Worldwide, a unit of the WarnerMedia News & Sports division of AT&T\'s WarnerMedia.',
        website='https://edition.cnn.com/'),
    Media(
        name='Fox News',
        description='Fox News, officially Fox News Channel, abbreviated FNC and commonly known as Fox, is an American multinational conservative cable news television channel based in New York City. It is owned by Fox News Media, which itself is owned by the Fox Corporation.',
        website='https://www.foxnews.com/'),
    Media(
        name='Independent',
        description='The Independent is a British online newspaper that was established in 1986 as a national morning printed paper. Nicknamed the Indy, it began as a broadsheet and changed to tabloid format in 2003.',
        website='https://www.independent.co.uk/'),
]


def create_media(media: Media) -> None:
    request = post(f'{API_URI}/medias', json=asdict(media))

    if request.status_code == 201:
        print(f'[INFO] {media.name} successfully created')
    else:
        print(f'[ERROR] Error HTTP {request.status_code} for {media.name} : {request.text}')


def create_medias(medias: List[Media]) -> None:
    [create_media(media) for media in medias]


if __name__ == '__main__':
    create_medias(DEFAULT_MEDIAS)
