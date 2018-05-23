package com.vs.routeadviser.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

public class Place { // of interest

    private final String name;
    private final double latitude;
    private final double longitude;

    private final Set<Category> categories;
    private Set<String> tags;

    public Place(String name, double latitude, double longitude, Category... categories) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tags = Sets.newHashSet();
        this.categories = Sets.newHashSet(Arrays.asList(categories));
    }

    public String getName() {
        return this.name;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public Set<String> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(this.categories);
    }

    public boolean addTags(Set<String> tags) {
        return this.tags.addAll(tags);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Place)) {
            return false;
        }
        Place other = (Place) obj;
        if (categories == null) {
            if (other.categories != null) {
                return false;
            }
        } else if (!categories.equals(other.categories)) {
            return false;
        }
        if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (tags == null) {
            if (other.tags != null) {
                return false;
            }
        } else if (!tags.equals(other.tags)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name + ", [latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    
}
