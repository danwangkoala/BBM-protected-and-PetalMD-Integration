import json
import sys
import datetime
sys.path.append("/home/d97wang/cs792/")
'''
def default(obj):
    """Default JSON serializer."""
    import calendar, datetime

    if isinstance(obj, datetime.datetime):
        if obj.utcoffset() is not None:
            obj = obj - obj.utcoffset()
        millis = int(
            calendar.timegm(obj.timetuple()) * 1000 +
            obj.microsecond / 1000
        )
        return millis
    raise TypeError('Not sure how to serialize %s' % (obj,))
'''


with open('first_name.txt') as f:
    first_name_list = f.read()
    first_name_list = first_name_list.split()
with open('last_name.txt') as f:
    last_name_list = f.read()
    last_name_list = last_name_list.split()   
    
print first_name_list[1]
print last_name_list[1]
    
data = []



num1 = 5
num2 = 5
num3 = 5
#this can be used to generate doctors that will be available from now 
for i in range(0, num1):
    linedata = {
        "first_name": first_name_list[i],
        "last_name": last_name_list[i],
        "license": "134235",
        "group_acronym":"Cardio-ABC",
        "email": "xxx@gmail.com",
        "start_timestamp": json.dumps(datetime.datetime.now().strftime('%Y:%m:%d:%H:%M:%S')),
        "end_timestamp":json.dumps((datetime.datetime.now()+datetime.timedelta(hours=3)).strftime('%Y:%m:%d:%H:%M:%S')),
        "console_task_display_name":"Cardio OR #23",
        "group_task_name":"Operating room 23",
        "group_task_abbreviation":"OR_23",
        "contact_kind_1":"mobile",
        "contact_number_1":"12345678",
        "contact_kind_2":"mobile",
        "contact_number_2":"12345678",
        "contact_kind_3":"mobile",
        "contact_number_3":"12345678",
        "contact_kind_4":"mobile",
        "contact_number_4":"12345678",   
    }
    data.append(linedata)

#this can be used to generate doctors that available in a past time
for i in range(num1, num1+num2):
    linedata = {
        "first_name": first_name_list[i],
        "last_name": last_name_list[i],
        "license": "134235",
        "group_acronym":"Cardio-ABC",
        "email": "xxx@gmail.com",
        "start_timestamp": json.dumps((datetime.datetime.now()-datetime.timedelta(hours = 10)).strftime('%Y:%m:%d:%H:%M:%S')),
        "end_timestamp":json.dumps((datetime.datetime.now()-datetime.timedelta(hours=3)).strftime('%Y:%m:%d:%H:%M:%S')),
        "console_task_display_name":"Cardio OR #23",
        "group_task_name":"Operating room 23",
        "group_task_abbreviation":"OR_23",
        "contact_kind_1":"mobile",
        "contact_number_1":"12345678",
        "contact_kind_2":"mobile",
        "contact_number_2":"12345678",
        "contact_kind_3":"mobile",
        "contact_number_3":"12345678",
        "contact_kind_4":"mobile",
        "contact_number_4":"12345678",   
    }
    data.append(linedata)
#this can be used to generate doctors that will be available
for i in range(num1+num2, num1+num2+num3):
    linedata = {
        "first_name": first_name_list[i],
        "last_name": last_name_list[i],
        "license": "134235",
        "group_acronym":"Cardio-ABC",
        "email": "xxx@gmail.com",
        "start_timestamp": json.dumps((datetime.datetime.now()+datetime.timedelta(hours = 3)).strftime('%Y:%m:%d:%H:%M:%S')),
        "end_timestamp":json.dumps((datetime.datetime.now()+datetime.timedelta(hours = 7)).strftime('%Y:%m:%d:%H:%M:%S')),
        "console_task_display_name":"Cardio OR #23",
        "group_task_name":"Operating room 23",
        "group_task_abbreviation":"OR_23",
        "contact_kind_1":"mobile",
        "contact_number_1":"12345678",
        "contact_kind_2":"mobile",
        "contact_number_2":"12345678",
        "contact_kind_3":"mobile",
        "contact_number_3":"12345678",
        "contact_kind_4":"mobile",
        "contact_number_4":"12345678",   
    }
    data.append(linedata)
    
# Writing JSON data
with open('seed.json', 'w') as f:
     json.dump(data, f)


def default(obj):
    """Default JSON serializer."""
    import calendar, datetime

    if isinstance(obj, datetime.datetime):
        if obj.utcoffset() is not None:
            obj = obj - obj.utcoffset()
        millis = int(
            calendar.timegm(obj.timetuple()) * 1000 +
            obj.microsecond / 1000
        )
        return millis
    raise TypeError('Not sure how to serialize %s' % (obj,))

