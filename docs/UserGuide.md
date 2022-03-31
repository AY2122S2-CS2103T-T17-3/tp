---
layout: page
title: User Guide
---

Automated Insurance Assistant (AIA) is a desktop application that helps Insurance Agents to manage their client contacts.
Instead of hiring a secretary to handle day-to-day activities, using AIA can help you achieve the following:
- Catalogue and easily retrieve saved data on clients based on specific categories
- Keep track of important events and time sensitive tasks to do for each client (e.g. client birthdays)
- Keep records of previous interactions with each client

Usage of this application is optimised for use via a Command Line Interface (CLI) while still displaying information to users in an intuitive manner, through a Graphical User Interface (GUI).
If you can type fast, AIA can significantly reduce the amount of time spent on retrieving saved information for each client that might have been saved on many different sources previously.

This user guide offers the following to insurance agents or other users trying to learn how to use the application:
- A quick start to running the application
- All available features
- Examples on how to use each feature

### Use of in-text formatting
Bold words refer to commands to be performed by users.

e.g. **cd** will refer to the "change directory" command in CLI.


Words in quotations are files, file types or dependencies.

e.g. "exe" refers to the executable file type used on windows.

----
<div style="page-break-after: always;"></div>

## Table of Contents

* [Quick Start](#quick-start)
* [Features](#features)
  - [Adding a contact: `add`](#adding-a-contact-add)
  - [Viewing all contacts: `list`](#viewing-all-contacts-list)
  - [Editing a contact: `edit`](#editing-a-contact-edit)
  - [Finding contacts by name: `find`](#finding-contacts-by-name-find)
  - [Deleting a contact: `delete`](#deleting-a-contact-delete)
  - [Adding a tag: `tag`](#adding-a-tag-tag)
  - [Deleting a tag: `untag`](#deleting-a-tag-untag)
  - [Viewiing all available tags: `tags`](#viewing-all-available-tags-tags)
  - [Finding contacts with tag: `#`](#finding-contacts-with-tag-)
  - [Adding interaction records with a contact: `log`](#adding-interaction-records-with-a-contact-log)
  - [Deleting a recent interaction record with a contact: `unlog`](#deleting-a-recent-interaction-record-with-a-contact-unlog)
  - [Viewing all recent interactions with a contact: `logs`](#viewing-all-recent-interactions-with-a-contact-logs)
  - [Viewing contacts that were contacted within days: `within`](#viewing-contacts-that-were-contacted-within-days-within)
  - [Viewing contacts contacted more than a specified number of days ago: `after`](#viewing-contacts-contacted-more-than-a-specified-number-of-days-ago-after)
  - [Adding a reminder to a contact: `remind`](#adding-a-reminder-to-a-contact-remind)
  - [Viewing reminders of a contact: `reminder`](#viewing-reminders-of-a-contact-reminder)
  - [Viewing reminders by date: `reminders`](#viewing-reminders-by-date-reminders)
  - [Show all birthdays today: `birthdays`](#show-all-birthdays-today-birthdays)
  - [Deleting a reminder: `forget`](#deleting-a-reminder-forget)
  - [Exiting the program: `exit`](#exiting-the-program-exit)
  - [Saving the data](#saving-the-data)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Quick Start

1. Ensure you have "Java JDK 11" or above installed in your Computer.

2. Download the latest "jar" file, "AIA.jar" [here](https://github.com/AY2122S2-CS2103T-T17-3/tp/releases).

3. Move the "jar" file to an empty folder in where you want to store the "jar" file and the contacts.

4. Open a shell application (Command Prompt or Terminal etc.) and navigate to the folder created in step 3.

5. Run the "jar" file with the **java -jar** command in the shell application to run the application, e.g. **java -jar AIA.jar**.

6. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.

7. When in doubt, type `help` in the command box or click "Help" in the application bar to pull up the list of available commands.

8. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Markup words in `UPPER_CASE` are the information to be entered by the user.<br>
  e.g. in `add n/NAME`, `NAME` is an information which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/Client` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Client`, `t/Client t/PremiumMember` etc.

* User input can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If an input is expected only once in for a specific command, but you specified it multiple times, only the last occurrence of
  the input will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Dates should be in "YYYY-MM-DD" format, unless stated otherwise. Single digits should be zero padded.<br>
  e.g. `2022-05-17`, `2021-01-01` and `2011-10-10` are in the correct format, while `2022-5-17` and `2021-1-1` are in the wrong format.
* `INDEX` used in the different commands refer to the index number shown in the displayed person list.

  ❗The `INDEX` must be a positive integer i.e. 1,2,3,...

</div>

<div style="page-break-after: always;"></div>

### Adding a contact: `add`

When you meet a new potential client and you managed to get their contact information, you can `add` them as a contact in the application.

* Upon adding a person, a default recently interacted date and description would be automatically generated.
  * The default date would be the day the contact was added, and default description would be "First Interaction".

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE [t/TAG]…​`
* `BIRTH_DATE` should be in the specified date format.

<div markdown="span" class="alert alert-primary">:bulb:
**Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/1970-01-01` adds the contact with only the compulsory information provided. <br>
  <img src="images/user-guide/AddCommandNoOptional.png"/>
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/1970-01-01 t/Client t/BasicMember` adds the contact with the compulsory information provided and the optional information. <br>
  <img src="images/user-guide/AddCommandWithOptional.png"/> <br>
  [Return to Table of Contents](#table-of-contents)

### Viewing all contacts: `list`

When you want to look through your whole clientele, `list` will show all the people saved in the application. <br>

Format: `list` <br>
<img src="images/user-guide/ListCommand.png"/>

[Return to Table of Contents](#table-of-contents)
<div style="page-break-after: always;"></div>

### Editing a contact: `edit`

If you realise you need to change a client's outdated information, you can `edit` an existing contact in the application.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTH_DATE]
[t/TAG]…​`

* Edits the person at the specified `INDEX`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.
* `BIRTH_DATE` should be in the specified date format.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively. <br>
  <img src="images/user-guide/EditCommandNamesAndTags.png"/>
* `edit 2 n/Betsy Crower t/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags. <br>
  <img src="images/user-guide/EditCommandPhoneAndEmail.png"/>

[Return to Table of Contents](#table-of-contents)
<div style="page-break-after: always;"></div>

### Finding contacts by name: `find`

Scrolling through a whole list of people to find a specific client takes a long time. Instead, you can `find` people using keywords of their name.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  <img src="images/user-guide/FindCommandAlexDavid.png"/>

[Return to Table of Contents](#table-of-contents)

### Deleting a contact: `delete`

If a contact entry is no longer relevant, you can `delete` the specified contact from the application.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.

Examples:
* `list` followed by `delete 2` deletes the 2nd contact in the application. <br>
  <img src="images/user-guide/DeleteCommand.png"/>
* `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command. <br>
  <img src="images/user-guide/DeleteCommandFindThenDelete.png"/>

[Return to Table of Contents](#table-of-contents)

### Adding a tag: `tag`

Suppose you want to catalogue a contact with a specific category like "client", you can `tag` the contact to a category, as specified by the index.

Format: `tag INDEX t/TAG`

Example:
* `tag 1 t/client` adds the "client" tag to the 1st contact in the display contact list. <br>
  <img src="images/user-guide/AddTagCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Deleting a tag: `untag`

If a contact is no longer a client, and you wish to remove the tag, you can simply `untag` contact from the category, as specified by the index.

Format: `untag INDEX t/TAG`

Example:
* `untag 1 t/client` deletes the "client" tag from the 1st contact in the display contact list. <br>
  <img src="images/user-guide/DeleteTagCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Viewing all available tags: `tags`

If you forget what tags you have currently, you can list all the tags used in the application, in alphabetical order.

Format: `tags` <br>
<img src="images/user-guide/ViewTagsCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Finding contacts with tag: `#`

If you want to focus on a specific category, you can find all the contacts with the specified tag assigned to them.

Format: `#TAG`

Example:
* `#classmates` finds all the contacts that are tagged to "classmates". <br>
  <img src="images/user-guide/FindTagsCommandClassmates.png"/>

[Return to Table of Contents](#table-of-contents)

### Adding interaction records with a contact: `log`

After meeting a client, you might want to write a note about the client, along with the meeting date. By using the `log` command, you can manually save your interaction with the person in the form of a note accompanied by a date.

Format: `log INDEX d/DATE des/description`
* `DATE` should be in the specified date format.

Example:
* `log 3 d/2022-02-11 des/Signed contract` manually logs the following interaction with the 3rd person in the displayed contact list, `Signed contract` on `2022-02-11`. <br>
  <img src="images/user-guide/AddContactedInfoCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Deleting a recent interaction record with a contact: `unlog`

Suppose you made a mistake while recording your interaction. You can easily `unlog` the saved interaction record from the specified contact.

Format: `unlog INDEX del/INDEX`

* `INDEX` signifies the specified contact.
* `INDEX` refers to the index number shown in the displayed contact list.
* `del/INDEX` signifies the interaction record to delete.
* `del/INDEX` refers to the index number shown in the contact's displayed list of interaction records.

Example:
* `unlog 2 del/6` deletes the 6th interaction record from the 2nd contact in the displayed contact list. <br>
  <img src="images/user-guide/DeleteContactedInfoCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Viewing all recent interactions with a contact: `logs`

If you want to review your interaction history with a contact, you can use `logs` to list all saved interaction records for the specified contact.

Format: `logs INDEX`

* `INDEX` signifies the specified contact.

Example:
* `logs 2` lists all recorded interactions with the 2nd contact in the displayed contact list. <br>
  <img src="images/user-guide/ViewContactedInfoCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Viewing contacts that were contacted within days: `within`

Occasionally, it might be hard to remember if you met someone recently. The `within` command allows you to show all contacts that you had interacted with within a specified range of days.

Format: `within DAYS`
* DAYS must be a positive integer.

Example:
* `within 12` shows a list of people that were last contacted within the past 12 days. <br>
  <img src="images/user-guide/ViewContactedInfoWithinDaysCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Viewing contacts contacted more than a specified number of days ago: `after`

When cold calling for new potential clients, you can consider looking through your old contacts instead. You can display a list of people that you have not been in contact with for more than a specified number of DAYS.

Format: `after DAYS`
* DAYS must be a positive integer.

Example:
* `after 50` shows a list of people that were last contacted more than 50 days ago. <br>
  <img src="images/user-guide/ViewContactedInfoAfterDaysCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Adding a reminder to a contact: `remind`

It is hard to keep track of everything all at once. Add a reminder for a client to `remind` you about tasks.

* `INDEX` signifies the specified contact.
* `INDEX` refers to the index number shown in the displayed contact list.

Format: `remind INDEX r/REMINDER rd/DATE`
* `DATE` should be in the specified date format.

Example:
* `remind 1 r/phone call rd/2022-09-11` sets up a reminder for you to make a phone call to the 1st person on 11 Sep 2022. <br>
  <img src="images/user-guide/AddReminderCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Viewing reminders of a contact: `reminder`

Sometimes, certain clients require more attention. You can view all reminders of tasks you need to do for these specific clients.

Format: `reminder INDEX`

Example:
* `reminder 1`shows you a list of tasks you need to do for the 1st person along with their corresponding deadlines. <br>
  <img src="images/user-guide/ViewReminderByContactCommand.png"/>

[Return to Table of Contents](#table-of-contents)
<div style="page-break-after: always;"></div>

### Viewing reminders by date: `reminders`

If you just want to look at the tasks you need to do by a specific date, you can display all reminders due by a specified date.

Format: `reminders [d/DATE]`
* `DATE` should be in the specified date format.

Example:
* `reminders d/2022-09-11` shows all reminders for 11 Sep 2022. <br>
  <img src="images/user-guide/ViewRemindersByDateCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Show all birthdays today: `birthdays`

As an insurance agent, it is important to keep track of your clients' birthdays. We strongly suggest you to use the `birthdays` command once a day to make sure you do not miss out on your valued clients' birthdays.

Format: `birthdays` <br>
<img src="images/user-guide/BirthdayCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Deleting a reminder: `forget`

If a reminder is no longer necessary, you can delete the specific reminder to make your reminder list cleaner and shorter.

Format: `forget INDEX del/INDEX`

Example:
* `forget 1 del/1` helps you delete the first reminder on the 1st person's list. <br>
  <img src="images/user-guide/DeleteReminderCommand.png"/>

[Return to Table of Contents](#table-of-contents)

### Exiting the program: `exit`

Exits the program.

Format: `exit`

[Return to Table of Contents](#table-of-contents)

### Saving the data

The application's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
If you made a mistake while manually editing the saved data, a backup save file would be generated in the same folder.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command summary

| Action                                                   | Format                                                                                |
|----------------------------------------------------------|---------------------------------------------------------------------------------------|
| **Add a contact**                                        | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTH_DATE [t/TAG]…​`                  |
| **View all contacts**                                    | `list`                                                                                |
| **Edit a contact**                                       | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTH_DATE] [t/TAG]…​` |
| **Find a contact by keywords**                           | `find KEYWORD [MORE_KEYWORDS]`                                                        |
| **Delete a contact**                                     | `delete INDEX`                                                                        |
| **Add a tag**                                            | `tag INDEX t/TAG`                                                                     |
| **Delete a tag**                                         | `untag INDEX t/TAG`                                                                   |
| **Show all tags**                                        | `tags`                                                                                |
| **Find contacts by tags**                                | `#`                                                                                   |
| **Record an interaction with a contact**                 | `log INDEX d/DATE des/description`                                                    |
| **Delete a recorded interaction with a contact**         | `unlog INDEX del/INDEX`                                                               |
| **View all recorded interactions with a contact**        | `logs INDEX`                                                                          |
| **View contacts contacted within DAYS**                  | `within DAYS`                                                                         |
| **View contacts you have contacted more than DAYS ago**  | `after DAYS`                                                                          |
| **View birthdays occurring today**                       | `birthdays`                                                                           |
| **Add a reminder**                                       | `remind INDEX r/REMINDER rd/DATE`                                                     |
| **View reminders of a contact**                          | `reminder INDEX`                                                                      |
| **View reminders on a date**                             | `reminders [rd/DATE]`                                                                 |
| **Delete a reminder**                                    | `forget INDEX del/INDEX`                                                              |
| **Exit the program**                                     | `exit`                                                                                |
| **Help**                                                 | `help`                                                                                |

[Return to Table of Contents](#table-of-contents)
