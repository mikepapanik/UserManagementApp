$(function() {
    // Αρχικά κρύψτε το μήνυμα σφάλματος
    $("#birthdate-error").hide();

    $("#birthdate").datepicker({
        dateFormat: "yy-mm-dd", // Συμβατή μορφή με SQL DATE.
        changeYear: true,
        yearRange: "1900:+nn", // Εύρος επιλέξιμων ετών (από το 1900 μέχρι το μέλλον).
        maxDate: 0, // Απενεργοποιεί τις ημερομηνίες μετά από την τρέχουσα ημερομηνία.
        showOtherMonths: true, // Εμφανίζει και τις ημέρες των προηγούμενων και επόμενων μηνών.
        selectOtherMonths: true, // Επιτρέπει την επιλογή ημερομηνιών από τους προηγούμενους και επόμενους μήνες.
        showButtonPanel: true, // Εμφανίζει ένα πάνελ επιλογής με κουμπιά "Σήμερα" και "Καθαρισμός".
        closeText: "Select", // Κείμενο κουμπιού για κλείσιμο του datepicker.
        currentText: "Today", // Κείμενο κουμπιού για επιλογή της τρέχουσας ημερομηνίας.
    });

    // Επιπλέον έλεγχος για τη σωστή μορφή της ημερομηνίας
    $("#birthdate").change(function() {
        var inputDate = $("#birthdate").val();
        var validDate = /^(\d{4})-(\d{2})-(\d{2})$/.test(inputDate);
        var errorField = $("#birthdate-error");

        if (!validDate) {
            errorField.text("Please enter a valid date in the format yyyy-mm-dd.").css("color", "red").show();
            $("#birthdate").val("");
        } else {
            errorField.hide(); // Αφαιρεί το μήνυμα σφάλματος και το κρύβει.
        }
    });
});

