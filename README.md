# Bread Boys
- Informational app for our personal Bread Boys company
- Incorporate an ordering tool for purchasing our items
- Recipe page with a viewpager
- Scroll list with bread items
- Contact info

## Git Config Instructions
`git config --global user.name` `<<<YOUR NAME HERE>>>`

`git config --global user.email` `<<<YOUR EMAIL HERE>>>`

## Commiting Instructions

Download/Clone the project from the repository

Perform the Git Config Instructions

Perform a `git branch -a` to see all the branches on the github repo

'git checkout staging' so that it creates a local copy of staging you can branch from

Create a branch that branches from staging and check it out `git checkout -b branchname`

When you are ready to put your work into staging (the main project) you do the following
Checkout staging `git checkout staging`

Pull staging (ensure your local staging is up to date with the copy on origin) 'git pull'

Checkout your branch `git checkout branchname`

Merge staging into your branch (This may cause a merge conflict if staging had content it downloaded) 'git merge staging'

Fix any issues that are in your project indicated by <<<<<<head ======== >>>>>>branchname

Once all issues are fixed perform another commit `git add .` and `git commit -m “”`
Checkout staging `git checkout staging`

Perform a git pull (to ensure staging did not change while we were making fixes) `git pull`

##  IMPORTANT (if there are no changes then we can proceed. If there are changes and updates are downloaded we repeat the above steps)

If no conflicts appeared and everything is up to date you are ready for your code to make its way into staging

Push your branch to github  `git push origin branchname`

Go to GitHub and perform a pull request (possibly tag a group member to get it done faster)

## Additional Tips
Document everything you do with JavaDoc Comments

With each commit comment the issue it is addressing `git commit -m "#4 this addresses isssue number 4"`

# Project Layout
- These are the images that outline the layout of our project

### Home Menu
<img width="302" alt="homepage" src="https://user-images.githubusercontent.com/32267705/35045996-aa5a08b4-fb63-11e7-8d03-08f91be87f91.png">

### Loaves Menu
<img width="300" alt="loavespage" src="https://user-images.githubusercontent.com/32267705/35046010-bc1bf3d2-fb63-11e7-9097-ec745576bb8a.png">

### Recipe Page
<img width="303" alt="recipepage" src="https://user-images.githubusercontent.com/32267705/35046032-cace0dfc-fb63-11e7-980c-43cc3bc84d83.png">

### Checkout Page
Without items              | With items
:-------------------------:|:-------------------------:
<img width="300" alt="checkoutempty" src="https://user-images.githubusercontent.com/32267705/35046060-e5b118a8-fb63-11e7-891d-ab501d0695ca.png">  |  <img width="301" alt="checkoutfull" src="https://user-images.githubusercontent.com/32267705/35046063-e6cee26a-fb63-11e7-9090-c8f5abc153bd.png">

### Contact Page
<img width="300" alt="contactpage" src="https://user-images.githubusercontent.com/32267705/35046077-f39878d0-fb63-11e7-83b1-b4d9c751b61d.png">

### Settings Page
<img width="299" alt="settingspage" src="https://user-images.githubusercontent.com/32267705/35046091-012ceb16-fb64-11e7-8914-bdb90eb4356f.png">
