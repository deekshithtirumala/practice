import hashlib

class Block:

	data = {}	hash = ""

	prev_hash = ""

	def __init__(self, data, prev_hash):

		self.data = data

		self.prev_hash = prev_hash

		self.hash = str(hashlib.sha256(bytes(str(self.data)+self.prev_hash, "utf-8")).hexdigest())

		

	def __str__(self):

		return self.hash

		

		

b = Block({"name":"deeks"}, str(hashlib.sha256(b'deeks').hexdigest()))

c = Block({"name":"magic"}, str(b))

print(b.prev_hash)

print(b)

print(c.prev_hash)

print(c)
