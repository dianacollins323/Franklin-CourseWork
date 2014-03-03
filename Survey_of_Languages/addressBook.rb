# Diana Collins assignment 8-1 Ruby programming

# define local variables
$i;
$fName
$lName
$phone
$address
$addressBook = Array.new

# 3 default contacts created and added to addressBook
contact1 = ["Diana", "Collins", "3305558899", "123 Some St Canton, Oh 44708"]
contact2 = ["David", "Smith", "4402223355", "456 Another St Cleveland, Oh 44126"]
contact3 = ["Olivia", "Smart", "2208883355", "789 Yet Another St Columbus, Oh 43758"]
$addressBook.push contact1
$addressBook.push contact2
$addressBook.push contact3

# add method, creates a new contact, adds new contact to addressBook
def add(fName, lName, phone, address)
    contact = Array.new
	contact.push fName
	contact.push lName
	contact.push phone
	contact.push address
	$addressBook.push contact
end

# prompts user for contact info and checks if contact already exists
def contactPrompt()
    puts("Enter first name:")
	$fName = gets.chomp
	puts("Enter last name:")
	$lName = gets.chomp
	puts("Enter phone number")
	$phone = gets.chomp
	puts("Enter street address:")
	street = gets.chomp
	puts("Enter city:")
	city = gets.chomp
	puts("Enter state:")
	state = gets.chomp
	puts("Enter zipcode:")
	zip = gets.chomp
	$address = street + " " + city + ", " + state + " " + zip
	
	# checking if contact already exists
	counter = 0
	for c in $addressBook
	    if $fName == c[0] && $lName == c[1]
	        counter += 1
	    end
	end
	if counter == 1
	    puts("This contact already exists")
	else
	    add($fName, $lName, $phone, $address)
	end
end

# sort address book into alphabetical order and print
def printAddressBook()
    # sort address book 
    sortedBook = $addressBook.sort_by { |x| x[1] }
    
    # print sorted address book
    for c in sortedBook
        $stdout.print(c[0] + " " + c[1] + ", " + c[2] + ", " + c[3] + "\n")
    end
end

# displays the requested contact given the first and last name is it exists
def retrieve(first, last)
    i = 0
    f = -1
    
    # find requested contact
    while i < $addressBook.length
        if first == $addressBook[i][0] && last == $addressBook[i][1]
            f = i
        end
        i += 1
    end
    
    # if contact exists print it
    if f != -1
        puts($addressBook[f][0] + " " + $addressBook[f][1] + ", " + $addressBook[f][2] + ", " + $addressBook[f][3] + "\n")
    else
        puts("This contact is not in the address book")
    end
end

# prompts user for the first and last name then passes them to the requested method (retrieve or remove)
def getFirstAndLast(method)
    puts("Enter first name")
	first = gets.chomp
	puts("Enter last name")
	last = gets.chomp
	
	if method == "retrieve"
	    retrieve(first, last)
	elsif method == "remove"
	    remove(first, last)
	end
end

# removes contact given the first and last name
def remove(first, last)
    i = 0
    f = -1
    
    # find requested contact
    while i < $addressBook.length
        if first == $addressBook[i][0] && last == $addressBook[i][1]
            f = i
        end
        i += 1
    end
    
    # if contact exists delete it
    if f != -1
        $addressBook.delete_at(f)
    else
        puts("This contact is not in the address book")
    end
end

# main program prompt loop
loop do
    puts("1: Add a contact")
    puts("2: Delete a contact")
    puts("3: Retrieve a contact")
    puts("4: Print all contacts")
    puts("5: Exit address book")
    puts("Please select a number from the above menu.")
    num = gets
    $i = num.to_i
    case
    when $i == 1
        contactPrompt()
    when $i == 2
        getFirstAndLast("remove")
    when $i == 3
        getFirstAndLast("retrieve")
    when $i == 4
        printAddressBook()
    when $i == 5
        exit
    else 
        puts("your entry is invalid")
    end
end