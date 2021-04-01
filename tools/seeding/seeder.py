from dataclasses import asdict
from random import choice
from typing import List

from requests import post

from media import Media
from vote import Vote


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


DEFAULT_VOTES_PER_MEDIA = 250


def create_media(media: Media) -> Media:
    request = post(f'{API_URI}/medias', json=asdict(media))

    if request.status_code != 201:
        exit(f'Error HTTP {request.status_code} for {media.name} : {request.text}')

    print(f'[INFO] {media.name} successfully created')

    media.id = int(request.json()['id'])
    return media


def create_medias(medias: List[Media]) -> List[Media]:
    return [create_media(media) for media in medias]


def generate_random_vote() -> Vote:
    return Vote(trustworthy=choice([True, False]))


def generate_vote_for_media(media: Media) -> Vote:
    vote = generate_random_vote()

    request = post(f'{API_URI}/medias/{media.id}/votes', json=asdict(vote))

    if request.status_code != 202:
        exit(f'Error HTTP {request.status_code} for {media.name} : {request.text}')

    return vote


def generate_votes_for_media(media: Media) -> None:
    vote_count = { True: 0, False: 0 }

    for _ in range(DEFAULT_VOTES_PER_MEDIA):
        submitted_vote = generate_vote_for_media(media)
        vote_count[submitted_vote.trustworthy] += 1

    print(f'[INFO] {sum(vote_count.values())} votes generated for {media.name} ' + \
        f'({vote_count[True]} "trustworthy", {vote_count[False]} "untrustworthy")')


def generate_votes_for_medias(medias: List[Media]) -> None:
    [generate_votes_for_media(media) for media in medias]


if __name__ == '__main__':
    created_medias = create_medias(DEFAULT_MEDIAS)
    generate_votes_for_medias(created_medias)
