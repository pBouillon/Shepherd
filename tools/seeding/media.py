from dataclasses import dataclass

@dataclass
class Media:
    description: str
    name: str
    website: str
    id: int = 0